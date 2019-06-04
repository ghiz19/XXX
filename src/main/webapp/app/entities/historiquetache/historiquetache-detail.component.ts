import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IHistoriquetache } from 'app/shared/model/historiquetache.model';

@Component({
    selector: 'jhi-historiquetache-detail',
    templateUrl: './historiquetache-detail.component.html'
})
export class HistoriquetacheDetailComponent implements OnInit {
    historiquetache: IHistoriquetache;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ historiquetache }) => {
            this.historiquetache = historiquetache;
        });
    }

    previousState() {
        window.history.back();
    }
}
