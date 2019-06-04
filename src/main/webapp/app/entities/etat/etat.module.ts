import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GmaovSharedModule } from 'app/shared';
import {
    EtatComponent,
    EtatDetailComponent,
    EtatUpdateComponent,
    EtatDeletePopupComponent,
    EtatDeleteDialogComponent,
    etatRoute,
    etatPopupRoute
} from './';

const ENTITY_STATES = [...etatRoute, ...etatPopupRoute];

@NgModule({
    imports: [GmaovSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [EtatComponent, EtatDetailComponent, EtatUpdateComponent, EtatDeleteDialogComponent, EtatDeletePopupComponent],
    entryComponents: [EtatComponent, EtatUpdateComponent, EtatDeleteDialogComponent, EtatDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GmaovEtatModule {}
