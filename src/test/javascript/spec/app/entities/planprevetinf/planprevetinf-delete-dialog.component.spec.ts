/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GmaovTestModule } from '../../../test.module';
import { PlanprevetinfDeleteDialogComponent } from 'app/entities/planprevetinf/planprevetinf-delete-dialog.component';
import { PlanprevetinfService } from 'app/entities/planprevetinf/planprevetinf.service';

describe('Component Tests', () => {
    describe('Planprevetinf Management Delete Component', () => {
        let comp: PlanprevetinfDeleteDialogComponent;
        let fixture: ComponentFixture<PlanprevetinfDeleteDialogComponent>;
        let service: PlanprevetinfService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GmaovTestModule],
                declarations: [PlanprevetinfDeleteDialogComponent]
            })
                .overrideTemplate(PlanprevetinfDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlanprevetinfDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanprevetinfService);
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
