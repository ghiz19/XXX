import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeequipement } from 'app/shared/model/typeequipement.model';

@Component({
    selector: 'jhi-typeequipement-detail',
    templateUrl: './typeequipement-detail.component.html'
})
export class TypeequipementDetailComponent implements OnInit {
    typeequipement: ITypeequipement;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ typeequipement }) => {
            this.typeequipement = typeequipement;
        });
    }

    previousState() {
        window.history.back();
    }
}
