import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GmaovSharedModule } from 'app/shared';
import {
    ServiceeComponent,
    ServiceeDetailComponent,
    ServiceeUpdateComponent,
    ServiceeDeletePopupComponent,
    ServiceeDeleteDialogComponent,
    serviceeRoute,
    serviceePopupRoute
} from './';

const ENTITY_STATES = [...serviceeRoute, ...serviceePopupRoute];

@NgModule({
    imports: [GmaovSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ServiceeComponent,
        ServiceeDetailComponent,
        ServiceeUpdateComponent,
        ServiceeDeleteDialogComponent,
        ServiceeDeletePopupComponent
    ],
    entryComponents: [ServiceeComponent, ServiceeUpdateComponent, ServiceeDeleteDialogComponent, ServiceeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GmaovServiceeModule {}
