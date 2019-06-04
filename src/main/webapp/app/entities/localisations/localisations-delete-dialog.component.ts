import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILocalisations } from 'app/shared/model/localisations.model';
import { LocalisationsService } from './localisations.service';

@Component({
    selector: 'jhi-localisations-delete-dialog',
    templateUrl: './localisations-delete-dialog.component.html'
})
export class LocalisationsDeleteDialogComponent {
    localisations: ILocalisations;

    constructor(
        protected localisationsService: LocalisationsService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.localisationsService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'localisationsListModification',
                content: 'Deleted an localisations'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-localisations-delete-popup',
    template: ''
})
export class LocalisationsDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ localisations }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(LocalisationsDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.localisations = localisations;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/localisations', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/localisations', { outlets: { popup: null } }]);
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
