import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IEquipement } from 'app/shared/model/equipement.model';

@Component({
    selector: 'jhi-equipement-detail',
    templateUrl: './equipement-detail.component.html'
})
export class EquipementDetailComponent implements OnInit {
    equipement: IEquipement;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ equipement }) => {
            this.equipement = equipement;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
