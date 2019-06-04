import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILocalisations } from 'app/shared/model/localisations.model';

@Component({
    selector: 'jhi-localisations-detail',
    templateUrl: './localisations-detail.component.html'
})
export class LocalisationsDetailComponent implements OnInit {
    localisations: ILocalisations;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ localisations }) => {
            this.localisations = localisations;
        });
    }

    previousState() {
        window.history.back();
    }
}
