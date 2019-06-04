import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IEquipe } from 'app/shared/model/equipe.model';
import { EquipeService } from './equipe.service';

@Component({
    selector: 'jhi-equipe-update',
    templateUrl: './equipe-update.component.html'
})
export class EquipeUpdateComponent implements OnInit {
    equipe: IEquipe;
    isSaving: boolean;

    constructor(protected equipeService: EquipeService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ equipe }) => {
            this.equipe = equipe;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.equipe.id !== undefined) {
            this.subscribeToSaveResponse(this.equipeService.update(this.equipe));
        } else {
            this.subscribeToSaveResponse(this.equipeService.create(this.equipe));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEquipe>>) {
        result.subscribe((res: HttpResponse<IEquipe>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
