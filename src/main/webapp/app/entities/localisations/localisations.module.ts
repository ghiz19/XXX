import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GmaovSharedModule } from 'app/shared';
import {
    LocalisationsComponent,
    LocalisationsDetailComponent,
    LocalisationsUpdateComponent,
    LocalisationsDeletePopupComponent,
    LocalisationsDeleteDialogComponent,
    localisationsRoute,
    localisationsPopupRoute
} from './';

const ENTITY_STATES = [...localisationsRoute, ...localisationsPopupRoute];

@NgModule({
    imports: [GmaovSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        LocalisationsComponent,
        LocalisationsDetailComponent,
        LocalisationsUpdateComponent,
        LocalisationsDeleteDialogComponent,
        LocalisationsDeletePopupComponent
    ],
    entryComponents: [
        LocalisationsComponent,
        LocalisationsUpdateComponent,
        LocalisationsDeleteDialogComponent,
        LocalisationsDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GmaovLocalisationsModule {}
