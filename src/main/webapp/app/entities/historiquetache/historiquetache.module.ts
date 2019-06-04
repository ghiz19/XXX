import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GmaovSharedModule } from 'app/shared';
import {
    HistoriquetacheComponent,
    HistoriquetacheDetailComponent,
    HistoriquetacheUpdateComponent,
    HistoriquetacheDeletePopupComponent,
    HistoriquetacheDeleteDialogComponent,
    historiquetacheRoute,
    historiquetachePopupRoute
} from './';

const ENTITY_STATES = [...historiquetacheRoute, ...historiquetachePopupRoute];

@NgModule({
    imports: [GmaovSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        HistoriquetacheComponent,
        HistoriquetacheDetailComponent,
        HistoriquetacheUpdateComponent,
        HistoriquetacheDeleteDialogComponent,
        HistoriquetacheDeletePopupComponent
    ],
    entryComponents: [
        HistoriquetacheComponent,
        HistoriquetacheUpdateComponent,
        HistoriquetacheDeleteDialogComponent,
        HistoriquetacheDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GmaovHistoriquetacheModule {}
