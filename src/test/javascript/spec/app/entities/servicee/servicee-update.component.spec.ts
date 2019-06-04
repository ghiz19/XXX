/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { GmaovTestModule } from '../../../test.module';
import { ServiceeUpdateComponent } from 'app/entities/servicee/servicee-update.component';
import { ServiceeService } from 'app/entities/servicee/servicee.service';
import { Servicee } from 'app/shared/model/servicee.model';

describe('Component Tests', () => {
    describe('Servicee Management Update Component', () => {
        let comp: ServiceeUpdateComponent;
        let fixture: ComponentFixture<ServiceeUpdateComponent>;
        let service: ServiceeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GmaovTestModule],
                declarations: [ServiceeUpdateComponent]
            })
                .overrideTemplate(ServiceeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ServiceeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ServiceeService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Servicee(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.servicee = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Servicee();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.servicee = entity;
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
