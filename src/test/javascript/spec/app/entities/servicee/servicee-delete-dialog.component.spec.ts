/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GmaovTestModule } from '../../../test.module';
import { ServiceeDeleteDialogComponent } from 'app/entities/servicee/servicee-delete-dialog.component';
import { ServiceeService } from 'app/entities/servicee/servicee.service';

describe('Component Tests', () => {
    describe('Servicee Management Delete Component', () => {
        let comp: ServiceeDeleteDialogComponent;
        let fixture: ComponentFixture<ServiceeDeleteDialogComponent>;
        let service: ServiceeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GmaovTestModule],
                declarations: [ServiceeDeleteDialogComponent]
            })
                .overrideTemplate(ServiceeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ServiceeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ServiceeService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
