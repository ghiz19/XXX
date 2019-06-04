import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeequipement } from 'app/shared/model/typeequipement.model';
import { TypeequipementService } from './typeequipement.service';

@Component({
    selector: 'jhi-typeequipement-delete-dialog',
    templateUrl: './typeequipement-delete-dialog.component.html'
})
export class TypeequipementDeleteDialogComponent {
    typeequipement: ITypeequipement;

    constructor(
        protected typeequipementService: TypeequipementService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.typeequipementService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'typeequipementListModification',
                content: 'Deleted an typeequipement'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-typeequipement-delete-popup',
    template: ''
})
export class TypeequipementDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ typeequipement }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TypeequipementDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.typeequipement = typeequipement;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/typeequipement', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/typeequipement', { outlets: { popup: null } }]);
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
