/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { GmaovTestModule } from '../../../test.module';
import { DemandeinterventionUpdateComponent } from 'app/entities/demandeintervention/demandeintervention-update.component';
import { DemandeinterventionService } from 'app/entities/demandeintervention/demandeintervention.service';
import { Demandeintervention } from 'app/shared/model/demandeintervention.model';

describe('Component Tests', () => {
    describe('Demandeintervention Management Update Component', () => {
        let comp: DemandeinterventionUpdateComponent;
        let fixture: ComponentFixture<DemandeinterventionUpdateComponent>;
        let service: DemandeinterventionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GmaovTestModule],
                declarations: [DemandeinterventionUpdateComponent]
            })
                .overrideTemplate(DemandeinterventionUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DemandeinterventionUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DemandeinterventionService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Demandeintervention(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.demandeintervention = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Demandeintervention();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.demandeintervention = entity;
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
