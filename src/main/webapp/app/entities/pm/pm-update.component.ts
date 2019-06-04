import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IPm } from 'app/shared/model/pm.model';
import { PmService } from './pm.service';
import { IEquipement } from 'app/shared/model/equipement.model';
import { EquipementService } from 'app/entities/equipement';

@Component({
    selector: 'jhi-pm-update',
    templateUrl: './pm-update.component.html'
})
export class PmUpdateComponent implements OnInit {
    pm: IPm;
    isSaving: boolean;

    equipements: IEquipement[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected pmService: PmService,
        protected equipementService: EquipementService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ pm }) => {
            this.pm = pm;
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
        if (this.pm.id !== undefined) {
            this.subscribeToSaveResponse(this.pmService.update(this.pm));
        } else {
            this.subscribeToSaveResponse(this.pmService.create(this.pm));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPm>>) {
        result.subscribe((res: HttpResponse<IPm>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
