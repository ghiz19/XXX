/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GmaovTestModule } from '../../../test.module';
import { HistoriquetacheDeleteDialogComponent } from 'app/entities/historiquetache/historiquetache-delete-dialog.component';
import { HistoriquetacheService } from 'app/entities/historiquetache/historiquetache.service';

describe('Component Tests', () => {
    describe('Historiquetache Management Delete Component', () => {
        let comp: HistoriquetacheDeleteDialogComponent;
        let fixture: ComponentFixture<HistoriquetacheDeleteDialogComponent>;
        let service: HistoriquetacheService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GmaovTestModule],
                declarations: [HistoriquetacheDeleteDialogComponent]
            })
                .overrideTemplate(HistoriquetacheDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(HistoriquetacheDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(HistoriquetacheService);
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
