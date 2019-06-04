import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GmaovSharedModule } from 'app/shared';
import {
    TypeequipementComponent,
    TypeequipementDetailComponent,
    TypeequipementUpdateComponent,
    TypeequipementDeletePopupComponent,
    TypeequipementDeleteDialogComponent,
    typeequipementRoute,
    typeequipementPopupRoute
} from './';

const ENTITY_STATES = [...typeequipementRoute, ...typeequipementPopupRoute];

@NgModule({
    imports: [GmaovSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TypeequipementComponent,
        TypeequipementDetailComponent,
        TypeequipementUpdateComponent,
        TypeequipementDeleteDialogComponent,
        TypeequipementDeletePopupComponent
    ],
    entryComponents: [
        TypeequipementComponent,
        TypeequipementUpdateComponent,
        TypeequipementDeleteDialogComponent,
        TypeequipementDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GmaovTypeequipementModule {}
