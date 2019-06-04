import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IPlanprevetinf } from 'app/shared/model/planprevetinf.model';
import { PlanprevetinfService } from './planprevetinf.service';
import { IEquipement } from 'app/shared/model/equipement.model';
import { EquipementService } from 'app/entities/equipement';

@Component({
    selector: 'jhi-planprevetinf-update',
    templateUrl: './planprevetinf-update.component.html'
})
export class PlanprevetinfUpdateComponent implements OnInit {
    planprevetinf: IPlanprevetinf;
    isSaving: boolean;

    equipements: IEquipement[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected planprevetinfService: PlanprevetinfService,
        protected equipementService: EquipementService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ planprevetinf }) => {
            this.planprevetinf = planprevetinf;
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
        if (this.planprevetinf.id !== undefined) {
            this.subscribeToSaveResponse(this.planprevetinfService.update(this.planprevetinf));
        } else {
            this.subscribeToSaveResponse(this.planprevetinfService.create(this.planprevetinf));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPlanprevetinf>>) {
        result.subscribe((res: HttpResponse<IPlanprevetinf>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
