import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IHistoriquetache } from 'app/shared/model/historiquetache.model';
import { HistoriquetacheService } from './historiquetache.service';
import { IInterevntion } from 'app/shared/model/interevntion.model';
import { InterevntionService } from 'app/entities/interevntion';
import { IUtilisateur } from 'app/shared/model/utilisateur.model';
import { UtilisateurService } from 'app/entities/utilisateur';

@Component({
    selector: 'jhi-historiquetache-update',
    templateUrl: './historiquetache-update.component.html'
})
export class HistoriquetacheUpdateComponent implements OnInit {
    historiquetache: IHistoriquetache;
    isSaving: boolean;

    interevntions: IInterevntion[];

    utilisateurs: IUtilisateur[];
    datetimedebut: string;
    detetimedebut: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected historiquetacheService: HistoriquetacheService,
        protected interevntionService: InterevntionService,
        protected utilisateurService: UtilisateurService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ historiquetache }) => {
            this.historiquetache = historiquetache;
            this.datetimedebut =
                this.historiquetache.datetimedebut != null ? this.historiquetache.datetimedebut.format(DATE_TIME_FORMAT) : null;
            this.detetimedebut =
                this.historiquetache.detetimedebut != null ? this.historiquetache.detetimedebut.format(DATE_TIME_FORMAT) : null;
        });
        this.interevntionService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IInterevntion[]>) => mayBeOk.ok),
                map((response: HttpResponse<IInterevntion[]>) => response.body)
            )
            .subscribe((res: IInterevntion[]) => (this.interevntions = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.utilisateurService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IUtilisateur[]>) => mayBeOk.ok),
                map((response: HttpResponse<IUtilisateur[]>) => response.body)
            )
            .subscribe((res: IUtilisateur[]) => (this.utilisateurs = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.historiquetache.datetimedebut = this.datetimedebut != null ? moment(this.datetimedebut, DATE_TIME_FORMAT) : null;
        this.historiquetache.detetimedebut = this.detetimedebut != null ? moment(this.detetimedebut, DATE_TIME_FORMAT) : null;
        if (this.historiquetache.id !== undefined) {
            this.subscribeToSaveResponse(this.historiquetacheService.update(this.historiquetache));
        } else {
            this.subscribeToSaveResponse(this.historiquetacheService.create(this.historiquetache));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IHistoriquetache>>) {
        result.subscribe((res: HttpResponse<IHistoriquetache>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackInterevntionById(index: number, item: IInterevntion) {
        return item.id;
    }

    trackUtilisateurById(index: number, item: IUtilisateur) {
        return item.id;
    }
}
