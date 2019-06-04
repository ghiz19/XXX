import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInterevntion } from 'app/shared/model/interevntion.model';
import { InterevntionService } from './interevntion.service';

@Component({
    selector: 'jhi-interevntion-delete-dialog',
    templateUrl: './interevntion-delete-dialog.component.html'
})
export class InterevntionDeleteDialogComponent {
    interevntion: IInterevntion;

    constructor(
        protected interevntionService: InterevntionService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.interevntionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'interevntionListModification',
                content: 'Deleted an interevntion'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-interevntion-delete-popup',
    template: ''
})
export class InterevntionDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ interevntion }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(InterevntionDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.interevntion = interevntion;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/interevntion', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/interevntion', { outlets: { popup: null } }]);
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
