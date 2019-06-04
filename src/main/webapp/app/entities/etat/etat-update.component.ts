import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IEtat } from 'app/shared/model/etat.model';
import { EtatService } from './etat.service';

@Component({
    selector: 'jhi-etat-update',
    templateUrl: './etat-update.component.html'
})
export class EtatUpdateComponent implements OnInit {
    etat: IEtat;
    isSaving: boolean;

    constructor(protected etatService: EtatService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ etat }) => {
            this.etat = etat;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.etat.id !== undefined) {
            this.subscribeToSaveResponse(this.etatService.update(this.etat));
        } else {
            this.subscribeToSaveResponse(this.etatService.create(this.etat));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEtat>>) {
        result.subscribe((res: HttpResponse<IEtat>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
