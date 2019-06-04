import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDemandeintervention } from 'app/shared/model/demandeintervention.model';
import { DemandeinterventionService } from './demandeintervention.service';

@Component({
    selector: 'jhi-demandeintervention-delete-dialog',
    templateUrl: './demandeintervention-delete-dialog.component.html'
})
export class DemandeinterventionDeleteDialogComponent {
    demandeintervention: IDemandeintervention;

    constructor(
        protected demandeinterventionService: DemandeinterventionService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.demandeinterventionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'demandeinterventionListModification',
                content: 'Deleted an demandeintervention'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-demandeintervention-delete-popup',
    template: ''
})
export class DemandeinterventionDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ demandeintervention }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DemandeinterventionDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.demandeintervention = demandeintervention;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/demandeintervention', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/demandeintervention', { outlets: { popup: null } }]);
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
