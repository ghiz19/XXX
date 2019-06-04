/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GmaovTestModule } from '../../../test.module';
import { PmDeleteDialogComponent } from 'app/entities/pm/pm-delete-dialog.component';
import { PmService } from 'app/entities/pm/pm.service';

describe('Component Tests', () => {
    describe('Pm Management Delete Component', () => {
        let comp: PmDeleteDialogComponent;
        let fixture: ComponentFixture<PmDeleteDialogComponent>;
        let service: PmService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GmaovTestModule],
                declarations: [PmDeleteDialogComponent]
            })
                .overrideTemplate(PmDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PmDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PmService);
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
