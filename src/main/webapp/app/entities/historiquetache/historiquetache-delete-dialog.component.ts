import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IHistoriquetache } from 'app/shared/model/historiquetache.model';
import { HistoriquetacheService } from './historiquetache.service';

@Component({
    selector: 'jhi-historiquetache-delete-dialog',
    templateUrl: './historiquetache-delete-dialog.component.html'
})
export class HistoriquetacheDeleteDialogComponent {
    historiquetache: IHistoriquetache;

    constructor(
        protected historiquetacheService: HistoriquetacheService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.historiquetacheService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'historiquetacheListModification',
                content: 'Deleted an historiquetache'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-historiquetache-delete-popup',
    template: ''
})
export class HistoriquetacheDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ historiquetache }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(HistoriquetacheDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.historiquetache = historiquetache;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/historiquetache', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/historiquetache', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
