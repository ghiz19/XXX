import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInterevntion } from 'app/shared/model/interevntion.model';

@Component({
    selector: 'jhi-interevntion-detail',
    templateUrl: './interevntion-detail.component.html'
})
export class InterevntionDetailComponent implements OnInit {
    interevntion: IInterevntion;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ interevntion }) => {
            this.interevntion = interevntion;
        });
    }

    previousState() {
        window.history.back();
    }
}
