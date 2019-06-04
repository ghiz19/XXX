import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEtat } from 'app/shared/model/etat.model';

@Component({
    selector: 'jhi-etat-detail',
    templateUrl: './etat-detail.component.html'
})
export class EtatDetailComponent implements OnInit {
    etat: IEtat;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ etat }) => {
            this.etat = etat;
        });
    }

    previousState() {
        window.history.back();
    }
}
