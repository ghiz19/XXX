/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { GmaovTestModule } from '../../../test.module';
import { InterevntionUpdateComponent } from 'app/entities/interevntion/interevntion-update.component';
import { InterevntionService } from 'app/entities/interevntion/interevntion.service';
import { Interevntion } from 'app/shared/model/interevntion.model';

describe('Component Tests', () => {
    describe('Interevntion Management Update Component', () => {
        let comp: InterevntionUpdateComponent;
        let fixture: ComponentFixture<InterevntionUpdateComponent>;
        let service: InterevntionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GmaovTestModule],
                declarations: [InterevntionUpdateComponent]
            })
                .overrideTemplate(InterevntionUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(InterevntionUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InterevntionService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Interevntion(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.interevntion = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Interevntion();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.interevntion = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
