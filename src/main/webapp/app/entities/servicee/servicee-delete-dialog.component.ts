import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IServicee } from 'app/shared/model/servicee.model';
import { ServiceeService } from './servicee.service';

@Component({
    selector: 'jhi-servicee-delete-dialog',
    templateUrl: './servicee-delete-dialog.component.html'
})
export class ServiceeDeleteDialogComponent {
    servicee: IServicee;

    constructor(protected serviceeService: ServiceeService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.serviceeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'serviceeListModification',
                content: 'Deleted an servicee'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-servicee-delete-popup',
    template: ''
})
export class ServiceeDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ servicee }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ServiceeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.servicee = servicee;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/servicee', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/servicee', { outlets: { popup: null } }]);
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
