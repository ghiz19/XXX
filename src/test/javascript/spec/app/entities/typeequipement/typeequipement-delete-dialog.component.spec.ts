/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GmaovTestModule } from '../../../test.module';
import { TypeequipementDeleteDialogComponent } from 'app/entities/typeequipement/typeequipement-delete-dialog.component';
import { TypeequipementService } from 'app/entities/typeequipement/typeequipement.service';

describe('Component Tests', () => {
    describe('Typeequipement Management Delete Component', () => {
        let comp: TypeequipementDeleteDialogComponent;
        let fixture: ComponentFixture<TypeequipementDeleteDialogComponent>;
        let service: TypeequipementService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GmaovTestModule],
                declarations: [TypeequipementDeleteDialogComponent]
            })
                .overrideTemplate(TypeequipementDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TypeequipementDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeequipementService);
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
