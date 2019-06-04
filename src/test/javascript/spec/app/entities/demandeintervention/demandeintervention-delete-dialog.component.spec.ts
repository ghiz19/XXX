/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GmaovTestModule } from '../../../test.module';
import { DemandeinterventionDeleteDialogComponent } from 'app/entities/demandeintervention/demandeintervention-delete-dialog.component';
import { DemandeinterventionService } from 'app/entities/demandeintervention/demandeintervention.service';

describe('Component Tests', () => {
    describe('Demandeintervention Management Delete Component', () => {
        let comp: DemandeinterventionDeleteDialogComponent;
        let fixture: ComponentFixture<DemandeinterventionDeleteDialogComponent>;
        let service: DemandeinterventionService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GmaovTestModule],
                declarations: [DemandeinterventionDeleteDialogComponent]
            })
                .overrideTemplate(DemandeinterventionDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DemandeinterventionDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DemandeinterventionService);
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
