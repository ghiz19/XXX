import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlanprevetinf } from 'app/shared/model/planprevetinf.model';
import { PlanprevetinfService } from './planprevetinf.service';

@Component({
    selector: 'jhi-planprevetinf-delete-dialog',
    templateUrl: './planprevetinf-delete-dialog.component.html'
})
export class PlanprevetinfDeleteDialogComponent {
    planprevetinf: IPlanprevetinf;

    constructor(
        protected planprevetinfService: PlanprevetinfService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.planprevetinfService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'planprevetinfListModification',
                content: 'Deleted an planprevetinf'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-planprevetinf-delete-popup',
    template: ''
})
export class PlanprevetinfDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planprevetinf }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PlanprevetinfDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.planprevetinf = planprevetinf;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/planprevetinf', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/planprevetinf', { outlets: { popup: null } }]);
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
