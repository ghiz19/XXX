/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { GmaovTestModule } from '../../../test.module';
import { PmUpdateComponent } from 'app/entities/pm/pm-update.component';
import { PmService } from 'app/entities/pm/pm.service';
import { Pm } from 'app/shared/model/pm.model';

describe('Component Tests', () => {
    describe('Pm Management Update Component', () => {
        let comp: PmUpdateComponent;
        let fixture: ComponentFixture<PmUpdateComponent>;
        let service: PmService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GmaovTestModule],
                declarations: [PmUpdateComponent]
            })
                .overrideTemplate(PmUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PmUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PmService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Pm(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.pm = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Pm();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.pm = entity;
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
