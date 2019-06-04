import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPm } from 'app/shared/model/pm.model';

@Component({
    selector: 'jhi-pm-detail',
    templateUrl: './pm-detail.component.html'
})
export class PmDetailComponent implements OnInit {
    pm: IPm;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pm }) => {
            this.pm = pm;
        });
    }

    previousState() {
        window.history.back();
    }
}
