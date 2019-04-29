import { NgModule } from '@angular/core';

import { GmaovSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [GmaovSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [GmaovSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class GmaovSharedCommonModule {}
