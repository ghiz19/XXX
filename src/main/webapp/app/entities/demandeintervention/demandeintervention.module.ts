import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GmaovSharedModule } from 'app/shared';
import {
    DemandeinterventionComponent,
    DemandeinterventionDetailComponent,
    DemandeinterventionUpdateComponent,
    DemandeinterventionDeletePopupComponent,
    DemandeinterventionDeleteDialogComponent,
    demandeinterventionRoute,
    demandeinterventionPopupRoute
} from './';

const ENTITY_STATES = [...demandeinterventionRoute, ...demandeinterventionPopupRoute];

@NgModule({
    imports: [GmaovSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DemandeinterventionComponent,
        DemandeinterventionDetailComponent,
        DemandeinterventionUpdateComponent,
        DemandeinterventionDeleteDialogComponent,
        DemandeinterventionDeletePopupComponent
    ],
    entryComponents: [
        DemandeinterventionComponent,
        DemandeinterventionUpdateComponent,
        DemandeinterventionDeleteDialogComponent,
        DemandeinterventionDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GmaovDemandeinterventionModule {}
