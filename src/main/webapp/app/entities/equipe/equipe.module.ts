import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GmaovSharedModule } from 'app/shared';
import {
    EquipeComponent,
    EquipeDetailComponent,
    EquipeUpdateComponent,
    EquipeDeletePopupComponent,
    EquipeDeleteDialogComponent,
    equipeRoute,
    equipePopupRoute
} from './';

const ENTITY_STATES = [...equipeRoute, ...equipePopupRoute];

@NgModule({
    imports: [GmaovSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [EquipeComponent, EquipeDetailComponent, EquipeUpdateComponent, EquipeDeleteDialogComponent, EquipeDeletePopupComponent],
    entryComponents: [EquipeComponent, EquipeUpdateComponent, EquipeDeleteDialogComponent, EquipeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GmaovEquipeModule {}
