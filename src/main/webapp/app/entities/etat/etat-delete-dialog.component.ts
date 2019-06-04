import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEtat } from 'app/shared/model/etat.model';
import { EtatService } from './etat.service';

@Component({
    selector: 'jhi-etat-delete-dialog',
    templateUrl: './etat-delete-dialog.component.html'
})
export class EtatDeleteDialogComponent {
    etat: IEtat;

    constructor(protected etatService: EtatService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.etatService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'etatListModification',
                content: 'Deleted an etat'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-etat-delete-popup',
    template: ''
})
export class EtatDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ etat }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EtatDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.etat = etat;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/etat', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/etat', { outlets: { popup: null } }]);
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
