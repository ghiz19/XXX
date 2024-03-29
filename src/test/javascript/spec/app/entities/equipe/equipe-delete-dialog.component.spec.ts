/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GmaovTestModule } from '../../../test.module';
import { EquipeDeleteDialogComponent } from 'app/entities/equipe/equipe-delete-dialog.component';
import { EquipeService } from 'app/entities/equipe/equipe.service';

describe('Component Tests', () => {
    describe('Equipe Management Delete Component', () => {
        let comp: EquipeDeleteDialogComponent;
        let fixture: ComponentFixture<EquipeDeleteDialogComponent>;
        let service: EquipeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GmaovTestModule],
                declarations: [EquipeDeleteDialogComponent]
            })
                .overrideTemplate(EquipeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EquipeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EquipeService);
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
