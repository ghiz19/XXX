import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPm } from 'app/shared/model/pm.model';
import { PmService } from './pm.service';

@Component({
    selector: 'jhi-pm-delete-dialog',
    templateUrl: './pm-delete-dialog.component.html'
})
export class PmDeleteDialogComponent {
    pm: IPm;

    constructor(protected pmService: PmService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.pmService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'pmListModification',
                content: 'Deleted an pm'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-pm-delete-popup',
    template: ''
})
export class PmDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pm }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PmDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.pm = pm;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/pm', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/pm', { outlets: { popup: null } }]);
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
