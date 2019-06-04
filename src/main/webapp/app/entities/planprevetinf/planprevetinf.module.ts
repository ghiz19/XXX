import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GmaovSharedModule } from 'app/shared';
import {
    PlanprevetinfComponent,
    PlanprevetinfDetailComponent,
    PlanprevetinfUpdateComponent,
    PlanprevetinfDeletePopupComponent,
    PlanprevetinfDeleteDialogComponent,
    planprevetinfRoute,
    planprevetinfPopupRoute
} from './';

const ENTITY_STATES = [...planprevetinfRoute, ...planprevetinfPopupRoute];

@NgModule({
    imports: [GmaovSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PlanprevetinfComponent,
        PlanprevetinfDetailComponent,
        PlanprevetinfUpdateComponent,
        PlanprevetinfDeleteDialogComponent,
        PlanprevetinfDeletePopupComponent
    ],
    entryComponents: [
        PlanprevetinfComponent,
        PlanprevetinfUpdateComponent,
        PlanprevetinfDeleteDialogComponent,
        PlanprevetinfDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GmaovPlanprevetinfModule {}
