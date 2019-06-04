import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GmaovSharedModule } from 'app/shared';
import {
    InterevntionComponent,
    InterevntionDetailComponent,
    InterevntionUpdateComponent,
    InterevntionDeletePopupComponent,
    InterevntionDeleteDialogComponent,
    interevntionRoute,
    interevntionPopupRoute
} from './';

const ENTITY_STATES = [...interevntionRoute, ...interevntionPopupRoute];

@NgModule({
    imports: [GmaovSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        InterevntionComponent,
        InterevntionDetailComponent,
        InterevntionUpdateComponent,
        InterevntionDeleteDialogComponent,
        InterevntionDeletePopupComponent
    ],
    entryComponents: [
        InterevntionComponent,
        InterevntionUpdateComponent,
        InterevntionDeleteDialogComponent,
        InterevntionDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GmaovInterevntionModule {}
