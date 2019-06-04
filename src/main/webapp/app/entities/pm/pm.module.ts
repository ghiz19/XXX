import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GmaovSharedModule } from 'app/shared';
import {
    PmComponent,
    PmDetailComponent,
    PmUpdateComponent,
    PmDeletePopupComponent,
    PmDeleteDialogComponent,
    pmRoute,
    pmPopupRoute
} from './';

const ENTITY_STATES = [...pmRoute, ...pmPopupRoute];

@NgModule({
    imports: [GmaovSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [PmComponent, PmDetailComponent, PmUpdateComponent, PmDeleteDialogComponent, PmDeletePopupComponent],
    entryComponents: [PmComponent, PmUpdateComponent, PmDeleteDialogComponent, PmDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GmaovPmModule {}
