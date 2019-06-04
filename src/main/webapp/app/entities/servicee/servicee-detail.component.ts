import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IServicee } from 'app/shared/model/servicee.model';

@Component({
    selector: 'jhi-servicee-detail',
    templateUrl: './servicee-detail.component.html'
})
export class ServiceeDetailComponent implements OnInit {
    servicee: IServicee;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ servicee }) => {
            this.servicee = servicee;
        });
    }

    previousState() {
        window.history.back();
    }
}
