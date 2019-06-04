import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ILocalisations } from 'app/shared/model/localisations.model';
import { LocalisationsService } from './localisations.service';

@Component({
    selector: 'jhi-localisations-update',
    templateUrl: './localisations-update.component.html'
})
export class LocalisationsUpdateComponent implements OnInit {
    localisations: ILocalisations;
    isSaving: boolean;

    constructor(protected localisationsService: LocalisationsService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ localisations }) => {
            this.localisations = localisations;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.localisations.id !== undefined) {
            this.subscribeToSaveResponse(this.localisationsService.update(this.localisations));
        } else {
            this.subscribeToSaveResponse(this.localisationsService.create(this.localisations));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ILocalisations>>) {
        result.subscribe((res: HttpResponse<ILocalisations>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
