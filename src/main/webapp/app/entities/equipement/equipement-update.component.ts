import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IEquipement } from 'app/shared/model/equipement.model';
import { EquipementService } from './equipement.service';
import { IDemandeintervention } from 'app/shared/model/demandeintervention.model';
import { DemandeinterventionService } from 'app/entities/demandeintervention';
import { ITypeequipement } from 'app/shared/model/typeequipement.model';
import { TypeequipementService } from 'app/entities/typeequipement';
import { ILocalisations } from 'app/shared/model/localisations.model';
import { LocalisationsService } from 'app/entities/localisations';
import { IServicee } from 'app/shared/model/servicee.model';
import { ServiceeService } from 'app/entities/servicee';

@Component({
    selector: 'jhi-equipement-update',
    templateUrl: './equipement-update.component.html'
})
export class EquipementUpdateComponent implements OnInit {
    equipement: IEquipement;
    isSaving: boolean;

    equipements: IEquipement[];

    demandeinterventions: IDemandeintervention[];

    typeequipements: ITypeequipement[];

    localisations: ILocalisations[];

    servicees: IServicee[];
    dateAchatDp: any;
    datedernieremaintenanceDp: any;
    dateexperationDp: any;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected equipementService: EquipementService,
        protected demandeinterventionService: DemandeinterventionService,
        protected typeequipementService: TypeequipementService,
        protected localisationsService: LocalisationsService,
        protected serviceeService: ServiceeService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ equipement }) => {
            this.equipement = equipement;
        });
        this.equipementService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IEquipement[]>) => mayBeOk.ok),
                map((response: HttpResponse<IEquipement[]>) => response.body)
            )
            .subscribe((res: IEquipement[]) => (this.equipements = res), (res: HttpErrorResponse) => this.onError(res.message));
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
        this.typeequipementService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ITypeequipement[]>) => mayBeOk.ok),
                map((response: HttpResponse<ITypeequipement[]>) => response.body)
            )
            .subscribe((res: ITypeequipement[]) => (this.typeequipements = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.localisationsService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ILocalisations[]>) => mayBeOk.ok),
                map((response: HttpResponse<ILocalisations[]>) => response.body)
            )
            .subscribe((res: ILocalisations[]) => (this.localisations = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.serviceeService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IServicee[]>) => mayBeOk.ok),
                map((response: HttpResponse<IServicee[]>) => response.body)
            )
            .subscribe((res: IServicee[]) => (this.servicees = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.equipement, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.equipement.id !== undefined) {
            this.subscribeToSaveResponse(this.equipementService.update(this.equipement));
        } else {
            this.subscribeToSaveResponse(this.equipementService.create(this.equipement));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEquipement>>) {
        result.subscribe((res: HttpResponse<IEquipement>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackDemandeinterventionById(index: number, item: IDemandeintervention) {
        return item.id;
    }

    trackTypeequipementById(index: number, item: ITypeequipement) {
        return item.id;
    }

    trackLocalisationsById(index: number, item: ILocalisations) {
        return item.id;
    }

    trackServiceeById(index: number, item: IServicee) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}
