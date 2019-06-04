import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IServicee } from 'app/shared/model/servicee.model';
import { ServiceeService } from './servicee.service';

@Component({
    selector: 'jhi-servicee-update',
    templateUrl: './servicee-update.component.html'
})
export class ServiceeUpdateComponent implements OnInit {
    servicee: IServicee;
    isSaving: boolean;

    constructor(protected serviceeService: ServiceeService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ servicee }) => {
            this.servicee = servicee;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.servicee.id !== undefined) {
            this.subscribeToSaveResponse(this.serviceeService.update(this.servicee));
        } else {
            this.subscribeToSaveResponse(this.serviceeService.create(this.servicee));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IServicee>>) {
        result.subscribe((res: HttpResponse<IServicee>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
