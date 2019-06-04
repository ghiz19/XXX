/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GmaovTestModule } from '../../../test.module';
import { InterevntionDeleteDialogComponent } from 'app/entities/interevntion/interevntion-delete-dialog.component';
import { InterevntionService } from 'app/entities/interevntion/interevntion.service';

describe('Component Tests', () => {
    describe('Interevntion Management Delete Component', () => {
        let comp: InterevntionDeleteDialogComponent;
        let fixture: ComponentFixture<InterevntionDeleteDialogComponent>;
        let service: InterevntionService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GmaovTestModule],
                declarations: [InterevntionDeleteDialogComponent]
            })
                .overrideTemplate(InterevntionDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(InterevntionDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InterevntionService);
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
