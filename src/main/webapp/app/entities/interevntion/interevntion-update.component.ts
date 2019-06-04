import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IInterevntion } from 'app/shared/model/interevntion.model';
import { InterevntionService } from './interevntion.service';
import { IDemandeintervention } from 'app/shared/model/demandeintervention.model';
import { DemandeinterventionService } from 'app/entities/demandeintervention';
import { IPlanprevetinf } from 'app/shared/model/planprevetinf.model';
import { PlanprevetinfService } from 'app/entities/planprevetinf';
import { IEtat } from 'app/shared/model/etat.model';
import { EtatService } from 'app/entities/etat';
import { IEquipe } from 'app/shared/model/equipe.model';
import { EquipeService } from 'app/entities/equipe';

@Component({
    selector: 'jhi-interevntion-update',
    templateUrl: './interevntion-update.component.html'
})
export class InterevntionUpdateComponent implements OnInit {
    interevntion: IInterevntion;
    isSaving: boolean;

    demandeinterventions: IDemandeintervention[];

    planprevetinfs: IPlanprevetinf[];

    etats: IEtat[];

    equipes: IEquipe[];
    datedebutintervention: string;
    datefinintervention: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected interevntionService: InterevntionService,
        protected demandeinterventionService: DemandeinterventionService,
        protected planprevetinfService: PlanprevetinfService,
        protected etatService: EtatService,
        protected equipeService: EquipeService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ interevntion }) => {
            this.interevntion = interevntion;
            this.datedebutintervention =
                this.interevntion.datedebutintervention != null ? this.interevntion.datedebutintervention.format(DATE_TIME_FORMAT) : null;
            this.datefinintervention =
                this.interevntion.datefinintervention != null ? this.interevntion.datefinintervention.format(DATE_TIME_FORMAT) : null;
        });
        this.demandeinterventionService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IDemandeintervention[]>) => mayBeOk.ok),
                map((response: HttpResponse<IDemandeintervention[]>) => response.body)
            )
            .subscribe(
                (res: IDemandeintervention[]) => (this.demandeinterventions = res),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.planprevetinfService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IPlanprevetinf[]>) => mayBeOk.ok),
                map((response: HttpResponse<IPlanprevetinf[]>) => response.body)
            )
            .subscribe((res: IPlanprevetinf[]) => (this.planprevetinfs = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.etatService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IEtat[]>) => mayBeOk.ok),
                map((response: HttpResponse<IEtat[]>) => response.body)
            )
            .subscribe((res: IEtat[]) => (this.etats = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.equipeService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IEquipe[]>) => mayBeOk.ok),
                map((response: HttpResponse<IEquipe[]>) => response.body)
            )
            .subscribe((res: IEquipe[]) => (this.equipes = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.interevntion.datedebutintervention =
            this.datedebutintervention != null ? moment(this.datedebutintervention, DATE_TIME_FORMAT) : null;
        this.interevntion.datefinintervention =
            this.datefinintervention != null ? moment(this.datefinintervention, DATE_TIME_FORMAT) : null;
        if (this.interevntion.id !== undefined) {
            this.subscribeToSaveResponse(this.interevntionService.update(this.interevntion));
        } else {
            this.subscribeToSaveResponse(this.interevntionService.create(this.interevntion));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IInterevntion>>) {
        result.subscribe((res: HttpResponse<IInterevntion>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackDemandeinterventionById(index: number, item: IDemandeintervention) {
        return item.id;
    }

    trackPlanprevetinfById(index: number, item: IPlanprevetinf) {
        return item.id;
    }

    trackEtatById(index: number, item: IEtat) {
        return item.id;
    }

    trackEquipeById(index: number, item: IEquipe) {
        return item.id;
    }
}
