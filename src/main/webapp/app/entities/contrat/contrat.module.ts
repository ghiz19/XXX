import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GmaovSharedModule } from 'app/shared';
import {
    ContratComponent,
    ContratDetailComponent,
    ContratUpdateComponent,
    ContratDeletePopupComponent,
    ContratDeleteDialogComponent,
    contratRoute,
    contratPopupRoute
} from './';

const ENTITY_STATES = [...contratRoute, ...contratPopupRoute];

@NgModule({
    imports: [GmaovSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ContratComponent,
        ContratDetailComponent,
        ContratUpdateComponent,
        ContratDeleteDialogComponent,
        ContratDeletePopupComponent
    ],
    entryComponents: [ContratComponent, ContratUpdateComponent, ContratDeleteDialogComponent, ContratDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GmaovContratModule {}
