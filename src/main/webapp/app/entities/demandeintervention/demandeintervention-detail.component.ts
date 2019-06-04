import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDemandeintervention } from 'app/shared/model/demandeintervention.model';

@Component({
    selector: 'jhi-demandeintervention-detail',
    templateUrl: './demandeintervention-detail.component.html'
})
export class DemandeinterventionDetailComponent implements OnInit {
    demandeintervention: IDemandeintervention;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ demandeintervention }) => {
            this.demandeintervention = demandeintervention;
        });
    }

    previousState() {
        window.history.back();
    }
}
