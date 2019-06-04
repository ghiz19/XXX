import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { IContrat } from 'app/shared/model/contrat.model';
import { ContratService } from './contrat.service';
import { IEquipement } from 'app/shared/model/equipement.model';
import { EquipementService } from 'app/entities/equipement';

@Component({
    selector: 'jhi-contrat-update',
    templateUrl: './contrat-update.component.html'
})
export class ContratUpdateComponent implements OnInit {
    contrat: IContrat;
    isSaving: boolean;

    equipements: IEquipement[];
    datedebutcontratDp: any;
    datefincontratDp: any;
    daterealisationDp: any;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected contratService: ContratService,
        protected equipementService: EquipementService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ contrat }) => {
            this.contrat = contrat;
        });
        this.equipementService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IEquipement[]>) => mayBeOk.ok),
                map((response: HttpResponse<IEquipement[]>) => response.body)
            )
            .subscribe((res: IEquipement[]) => (this.equipements = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.contrat.id !== undefined) {
            this.subscribeToSaveResponse(this.contratService.update(this.contrat));
        } else {
            this.subscribeToSaveResponse(this.contratService.create(this.contrat));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IContrat>>) {
        result.subscribe((res: HttpResponse<IContrat>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackEquipementById(index: number, item: IEquipement) {
        return item.id;
    }
}
