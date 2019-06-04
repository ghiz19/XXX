/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GmaovTestModule } from '../../../test.module';
import { LocalisationsDeleteDialogComponent } from 'app/entities/localisations/localisations-delete-dialog.component';
import { LocalisationsService } from 'app/entities/localisations/localisations.service';

describe('Component Tests', () => {
    describe('Localisations Management Delete Component', () => {
        let comp: LocalisationsDeleteDialogComponent;
        let fixture: ComponentFixture<LocalisationsDeleteDialogComponent>;
        let service: LocalisationsService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GmaovTestModule],
                declarations: [LocalisationsDeleteDialogComponent]
            })
                .overrideTemplate(LocalisationsDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(LocalisationsDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LocalisationsService);
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
