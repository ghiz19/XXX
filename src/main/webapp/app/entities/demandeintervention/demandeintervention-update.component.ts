import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { IDemandeintervention } from 'app/shared/model/demandeintervention.model';
import { DemandeinterventionService } from './demandeintervention.service';
import { IUtilisateur } from 'app/shared/model/utilisateur.model';
import { UtilisateurService } from 'app/entities/utilisateur';

@Component({
    selector: 'jhi-demandeintervention-update',
    templateUrl: './demandeintervention-update.component.html'
})
export class DemandeinterventionUpdateComponent implements OnInit {
    demandeintervention: IDemandeintervention;
    isSaving: boolean;

    utilisateurs: IUtilisateur[];
    datedemandeDp: any;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected demandeinterventionService: DemandeinterventionService,
        protected utilisateurService: UtilisateurService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ demandeintervention }) => {
            this.demandeintervention = demandeintervention;
        });
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
        if (this.demandeintervention.id !== undefined) {
            this.subscribeToSaveResponse(this.demandeinterventionService.update(this.demandeintervention));
        } else {
            this.subscribeToSaveResponse(this.demandeinterventionService.create(this.demandeintervention));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IDemandeintervention>>) {
        result.subscribe((res: HttpResponse<IDemandeintervention>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackUtilisateurById(index: number, item: IUtilisateur) {
        return item.id;
    }
}
