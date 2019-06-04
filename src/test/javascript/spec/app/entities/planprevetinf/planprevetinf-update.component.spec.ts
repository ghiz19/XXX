/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { GmaovTestModule } from '../../../test.module';
import { PlanprevetinfUpdateComponent } from 'app/entities/planprevetinf/planprevetinf-update.component';
import { PlanprevetinfService } from 'app/entities/planprevetinf/planprevetinf.service';
import { Planprevetinf } from 'app/shared/model/planprevetinf.model';

describe('Component Tests', () => {
    describe('Planprevetinf Management Update Component', () => {
        let comp: PlanprevetinfUpdateComponent;
        let fixture: ComponentFixture<PlanprevetinfUpdateComponent>;
        let service: PlanprevetinfService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GmaovTestModule],
                declarations: [PlanprevetinfUpdateComponent]
            })
                .overrideTemplate(PlanprevetinfUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PlanprevetinfUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanprevetinfService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Planprevetinf(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planprevetinf = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Planprevetinf();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planprevetinf = entity;
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
