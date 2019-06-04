import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ITypeequipement } from 'app/shared/model/typeequipement.model';
import { TypeequipementService } from './typeequipement.service';

@Component({
    selector: 'jhi-typeequipement-update',
    templateUrl: './typeequipement-update.component.html'
})
export class TypeequipementUpdateComponent implements OnInit {
    typeequipement: ITypeequipement;
    isSaving: boolean;

    constructor(protected typeequipementService: TypeequipementService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ typeequipement }) => {
            this.typeequipement = typeequipement;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.typeequipement.id !== undefined) {
            this.subscribeToSaveResponse(this.typeequipementService.update(this.typeequipement));
        } else {
            this.subscribeToSaveResponse(this.typeequipementService.create(this.typeequipement));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeequipement>>) {
        result.subscribe((res: HttpResponse<ITypeequipement>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
