import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPlanprevetinf } from 'app/shared/model/planprevetinf.model';

@Component({
    selector: 'jhi-planprevetinf-detail',
    templateUrl: './planprevetinf-detail.component.html'
})
export class PlanprevetinfDetailComponent implements OnInit {
    planprevetinf: IPlanprevetinf;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planprevetinf }) => {
            this.planprevetinf = planprevetinf;
        });
    }

    previousState() {
        window.history.back();
    }
}
